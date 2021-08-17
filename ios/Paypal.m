#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(Paypal, NSObject)

RCT_EXTERN_METHOD(
                  startWithOrderId:(NSDictionary *)options
                  resolver:(RCTPromiseResolveBlock *)resolve
                  rejecter:(RCTPromiseRejectBlock *)reject
                  )

@end
