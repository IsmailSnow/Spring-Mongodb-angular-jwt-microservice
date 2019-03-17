import { Role } from "./role";

export class User {
    id: number;
    username: string;
    birthday:string;
    email:string;
    firstName: string;
    lastName: string;
    token: string;
    roles:Role[];
    actived:boolean;
}