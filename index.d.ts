declare module 'react-native-paypal' {
  export interface StartWithOrderIdOptions {
    clientId: string;
    useSandbox: boolean;
    returnUrl: string;
    orderId: string;
    cancelErrorCode?: string;
  }

  export interface PaypalApprovalData {
    payerId: string;
    orderId: string;
    paymentId?: string;
  }

  interface PaypalModule {
    startWithOrderId(
      options: StartWithOrderIdOptions
    ): Promise<PaypalApprovalData>;
  }

  const Paypal: PaypalModule;

  export default Paypal;
}
