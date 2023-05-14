import { Customer } from "./customer";
import { CustomerAccount } from "./customer-account";

export interface CustomerCustomerAccount {
    customerDto: Customer;
    customerAccountDtoList: CustomerAccount[];
}
