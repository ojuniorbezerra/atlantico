import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { Router } from '@angular/router';

import { User } from '@app/_models';
import { UserService, AuthenticationService, AlertService } from '@app/_services';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  loading = false;
  displayedColumns: string[] = ['username', 'email'];
  data: User[] = [];
  isLoadingResults = true;

  users: User[] = [];

  constructor(
    private router: Router,
      private authenticationService: AuthenticationService,
      private userService: UserService,
      private alertService: AlertService
  ) {
    
  }

  ngOnInit() {
    this.loadAllUsers();
}
  addUser(): void {
    this.router.navigate(['/user-add']);
  };
  
  deleteUser(user: User): void {
    this.userService.delete(user.email)
      .subscribe( data => {
          this.loadAllUsers()
      })
  };
  editUser(user: User): void {
    this.router.navigate(['user-edit', user.email]);
  };
  loadAllUsers() {
    this.loading = true;
    this.userService.getAll().pipe(first()).subscribe(users => {
        this.users = users;
        this.loading = false;
    },
    error => {
        this.alertService.error(error);
        this.loading = false;
    });
  }

}
