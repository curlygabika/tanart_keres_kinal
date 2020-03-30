import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClassListComponent } from './class-list/class-list.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatToolbarModule, MatIconModule, MatButtonModule, MatFormFieldModule, MatInputModule, MatRippleModule, MatSelectModule } from '@angular/material';
import { ClassFormComponent } from './class-form/class-form.component';
import { ClassDetailComponent } from './class-detail/class-detail.component';
import { RoutingModule } from './routing/routing.module';
import { RegisterFormComponent } from './register-form/register-form.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { MainPageComponent } from './main-page/main-page.component';
import { HttpClientModule } from "@angular/common/http";
import { ClassTableComponent } from './class-table/class-table.component';
import { ClassTableRowComponent } from './class-table-row/class-table-row.component';
import { MyClassesComponent } from './my-classes/my-classes.component';


@NgModule({
  declarations: [
    AppComponent,
    ClassListComponent,
    ClassFormComponent,
    ClassDetailComponent,
    RegisterFormComponent,
    LoginFormComponent,
    MainPageComponent,
    ClassTableComponent,
    ClassTableRowComponent,
    MyClassesComponent
  ],
  imports: [
    HttpClientModule,
    RoutingModule,
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    NgbModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatRippleModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
