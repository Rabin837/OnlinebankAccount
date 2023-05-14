import { Customer } from "./customer";

export class PaginationData {
    customer: Customer[];
    totalCustomerNumber:number;
    currentPageNumber:number;
    totalPage:number;
}
