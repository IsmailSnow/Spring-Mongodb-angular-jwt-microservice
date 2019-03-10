import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authenticationService:AuthenticationService) { }

  ngOnInit() {
  }
  
  onLogin(f){
    console.log(f);
    this.authenticationService.login(f).subscribe(resp=>{
      console.log("result : "+resp.headers['authorization'])}
  ,error=>{console.log(error)});
  

}
}