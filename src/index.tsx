import { NativeModules } from 'react-native';

type PaypalType = {
  multiply(a: number, b: number): Promise<number>;
};

const { Paypal } = NativeModules;

export default Paypal as PaypalType;
