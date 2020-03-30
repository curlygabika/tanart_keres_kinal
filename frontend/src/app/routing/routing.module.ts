import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClassListComponent } from "../class-list/class-list.component";
import { ClassFormComponent } from "../class-form/class-form.component";
import { ClassDetailComponent } from "../class-detail/class-detail.component";
import { RegisterFormComponent } from "../register-form/register-form.component";
import { LoginFormComponent } from "../login-form/login-form.component";
import { MainPageComponent } from "../main-page/main-page.component";
import { AuthGuard } from '../auth.guard';
import { MyClassesComponent } from '../my-classes/my-classes.component';


const routes: Routes = [
  {
    path: '',
    redirectTo: '/main-page',
    pathMatch: 'full'
  },
  {
    path: 'main-page',
    component: MainPageComponent
  },
  {
    path: 'classes',
    component: ClassListComponent,
    //canActivate: [AuthGuard]
  },
  {
    path: 'myclasses',
    component: MyClassesComponent,
    //canActivate: [AuthGuard]
  },
  {
    path: 'classes/new',
    component: ClassFormComponent
  },
  {
    path: 'classes/:id',
    component: ClassDetailComponent
  },
  {
    path: 'signup',
    component: RegisterFormComponent
  },
  {
    path: 'login',
    component: LoginFormComponent
  },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes)  ],
  exports: [ RouterModule ],
  declarations: []
})
export class RoutingModule { }