import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '@app/_models';
import { UserService} from '@app/_services';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.scss']
})
export class UserAddComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private router: Router, private userService: UserService) { }

  addForm: FormGroup;
  authorities = [
   "ROLE_ADMIN",
    "ROLE_USER"
  ];
  ngOnInit() {
    this.addForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      authorities: ['', Validators.required]
    });

  }

  onSubmit() {
    let user:User = this.addForm.value
    user.authorities = [this.addForm.value.authorities]
    this.userService.register(user)
      .subscribe( data => {
        this.router.navigate(['users']);
      });
  }

}
