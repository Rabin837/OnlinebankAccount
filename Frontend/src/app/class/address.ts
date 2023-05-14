export interface Address {
    id:number;
   country:number;
   states:string;
  districts:string;
  municipalities:string;
}
export interface Countries{
id:number;
name:string;
}
export interface States{
id:string;
name:string;
districts:Array<Districts>;
}
export interface Districts{
id:string;
name:string;
states:Array<States>;
municipalities:Array<Municipality>;
}
export interface Municipality{
id:string;
name:string;
type:string;
}