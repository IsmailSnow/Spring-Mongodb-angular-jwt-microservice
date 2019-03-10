import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public host1:string="http://localhost:808O";

  constructor(private http:HttpClient) { }

  login(data){
    return this.http.post(this.host1+"/login",data,{observe:'response'});
  }
}
