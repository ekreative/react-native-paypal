# react-native-paypal

# Getting started

### Installation

```sh
npm install react-native-paypal
```

### Android Specific

Add this to your `build.gradle`
```groovy
maven {
  url  "https://cardinalcommerceprod.jfrog.io/artifactory/android"
  credentials {
    username 'paypal_sgerritz'
    password 'AKCp8jQ8tAahqpT5JjZ4FRP2mW7GMoFZ674kGqHmupTesKeAY2G8NcmPKLuTxTGkKjDLRzDUQ'
  }
}
```

If you already use [`@ekreative/react-native-braintree`](https://github.com/ekreative/react-native-braintree) package in your app you should to remove this lines from your `build.gradle`

```groovy
maven {
  url  "https://cardinalcommerce.bintray.com/android"
  credentials {
    username 'braintree-team-sdk@cardinalcommerce'
    password '220cc9476025679c4e5c843666c27d97cfb0f951'
  }
}
```

### iOS Specific
TODO

## Usage

```js
import Paypal from "react-native-paypal";

// ...

Paypal.startWithOrderId({
  clientId: 'YOUR_CLIENT_ID',
  useSandbox: true, // you should use 'false' for live environment
  orderId: 'YOUR_ODRED_ID', //should be fetched from your backend
  returnUrl: 'YOUR_RETURN_URL',
  cancelErrorCode: 'ON_CANCEL', //optional, default value - PAYPAL_CANCELLED
})
  .then(approvalData => console.log(approvalData))
  .catch(error => console.log(error));


```

## License

MIT
