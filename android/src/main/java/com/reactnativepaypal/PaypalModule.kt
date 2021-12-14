package com.reactnativepaypal

import android.app.Application
import com.facebook.react.bridge.*
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.error.OnError
import com.reactnativepaypal.Constants.PAYPAL_CANCEL_DEFAULT_CODE
import com.reactnativepaypal.Constants.PAYPAL_ERROR_CODE
import com.reactnativepaypal.Constants.PAYPAL_INTERNAL_ERROR_CODE

class PaypalModule(private val reactContext: ReactApplicationContext?) :
  ReactContextBaseJavaModule(reactContext) {
  override fun getName() = "Paypal"

  @ReactMethod
  fun startWithOrderId(parameters: ReadableMap, promise: Promise) {
    try {
      PayPalCheckout.setConfig(
        CheckoutConfig(
          clientId = parameters.getString("clientId") ?: "",
          environment = if (parameters.getBoolean("useSandbox"))
            Environment.SANDBOX else
            Environment.LIVE,
          returnUrl = parameters.getString("returnUrl") ?: "",
          application = reactContext?.applicationContext as Application
        )
      )

      PayPalCheckout.start(
        createOrder = CreateOrder { createOrderActions ->
          val orderId = parameters.getString("orderId") ?: ""
          createOrderActions.set(orderId)
        },
        onApprove = OnApprove { approval ->
          run {
            val result = Arguments.createMap()
            result.putString("orderId", approval.data.orderId)
            result.putString("payerId", approval.data.payerId)
            result.putString("paymentId", approval.data.paymentId)
            promise.resolve(result)
          }
        },
        onCancel = OnCancel {
          promise.reject(
            parameters.getString("cancelErrorCode") ?: PAYPAL_CANCEL_DEFAULT_CODE,
            "The user cancelled"
          )
        },
        onError = OnError { errorInfo -> promise.reject(PAYPAL_ERROR_CODE, errorInfo.reason) }
      )
    } catch (e: Exception) {
      promise.reject(PAYPAL_INTERNAL_ERROR_CODE, "Something went wrong on PayPal side. Please, try again.")
    }
  }
}