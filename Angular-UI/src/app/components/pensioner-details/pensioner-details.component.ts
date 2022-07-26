import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PensionerDetail } from 'src/app/models/pensioner-detail';
import { AuthService } from 'src/app/services/auth.service';
import { PensionerDetailsService } from 'src/app/services/pensioner-details.service';

@Component({
  selector: 'app-pensioner-details',
  templateUrl: './pensioner-details.component.html',
  styleUrls: ['./pensioner-details.component.css']
})
export class PensionerDetailsComponent implements OnInit {

  msg: String = ''
  color: String = ''
  aadharNo: String=''
  pensionerDetails= new PensionerDetail("","","","","");

  constructor(
    private pdservice: PensionerDetailsService,
    private authservice: AuthService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  handleReset() {
    this.msg = "";
    this.pensionerDetails= new PensionerDetail("","","","","");
  }

  handleAadhaarInput() {
    console.log(this.aadharNo);

    this.pdservice.getPensionerDetails(this.aadharNo)
      .subscribe(
        data => {
          this.color = "text-success"
          this.pensionerDetails = data
          this.msg ="Details Fetched!!"
         },
        error => {
          try {
            // get the errors thrown by the server
            this.msg = "Wrong Aadhaar Number"

          } catch (e) {
            // feign error if field error can't be parsed ...
            this.msg = "Wrong Aadhaar Number"
            this.color = "text-danger"
            console.log(this.msg);
          }
        }
      );
  }

  logoutIfTokenExpired(error: String) {
    if (error.includes("expired")) {
      alert("Your session has been expired... Logging out!");
      this.authservice.logout();
    }
  }

}
