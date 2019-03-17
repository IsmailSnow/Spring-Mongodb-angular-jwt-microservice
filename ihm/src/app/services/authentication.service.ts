import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  currentUserValue:any;
  isUserLoggedIn:any;
  currentUser:any;
  private currentUserSubject: BehaviorSubject<User>;
  constructor() { }


  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
}
login(username: string, password: string) {
  return this.currentUser;
}
}
