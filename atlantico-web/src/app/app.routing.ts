import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { AuthGuard } from './_guards';

import {  UsersComponent } from './users/users.component';
import { UserAddComponent } from './user-add/user-add.component';
import { UserEditComponent } from './user-edit/user-edit.component';

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    {
        path: 'users',
        component: UsersComponent,
        data: { title: 'List of Products' }
      },
      {
        path: 'user-add',
        component: UserAddComponent,
        data: { title: 'Add User' }
      },
      {
        path: 'user-edit/:email',
        component: UserEditComponent,
        data: { title: 'Edit User' }
      },
      { path: '',
        redirectTo: '/users',
        pathMatch: 'full'
      },
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);