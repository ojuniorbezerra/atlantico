import { Component, OnInit , Inject} from '@angular/core';
import {Router, ActivatedRoute} from "@angular/router";
import { User } from '@app/_models';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {first} from "rxjs/operators";
import { UserService, AuthenticationService, AlertService } from '@app/_services';
@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {

  authorities = [
    "ROLE_ADMIN",
     "ROLE_USER"
   ];
  user: User;
  editForm: FormGroup;
  constructor(private formBuilder: FormBuilder,
    private router: Router, 
    private activatedRoute: ActivatedRoute,
    private userService: UserService) { }

  ngOnInit() {
    let email:string = this.activatedRoute.snapshot.params.email;
    console.log(email)
    if(!email) {
      alert("Invalid action.")
      this.router.navigate(['users']);
      return;
    }
    this.editForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      authorities: ['', Validators.required]
    });
    this.userService.getUserByEmail(email)
      .subscribe( data => {
        this.editForm.get('username').setValue(data.username);
        this.editForm.get('email').setValue(data.email);
        this.editForm.get('authorities').setValue(data.authorities[0]);
      });
  }

  onSubmit() {
    let user:User = this.editForm.value
    user.authorities = [this.editForm.value.authorities]
    this.userService.update(user)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate(['/users']);
        },
        error => {
          alert(error);
        });
  }
}
