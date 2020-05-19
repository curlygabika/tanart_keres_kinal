import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpHeaders, HttpClient } from '@angular/common/http';

export const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json",
    Authorization: "",
    Body: ""
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public isLoggedIn: boolean = false;
  public user: User;
  public redirectUrl: string;
  private authUrl: string = "http://localhost:8080/user";

  constructor(private http: HttpClient) { }

  async signin(userName: string, fullName: string, password: string) {
    try {
      const token = btoa(`andrea112:password`);
      httpOptions.headers = httpOptions.headers.set(
        "Authorization",
        `Basic ${token}`
      );
      httpOptions.headers = httpOptions.headers.set(
        "Body",
        `username : ${userName}, password : ${password}, fullName : ${fullName}`
      );

      console.log(userName);
      console.log(password);
      console.log(fullName);

      const user = await this.http
        .post<User>(`${this.authUrl}/register`, {userName, fullName, password}, httpOptions)
        .toPromise();
      this.user = user;
      return Promise.resolve(this.user);
    } catch (e) {
      console.log(e);
      return Promise.reject();
    }
  }

  async login(username: string, password: string): Promise<User> {
    try {
      const token = btoa(`${username}:${password}`);
      httpOptions.headers = httpOptions.headers.set(
        "Authorization",
        `Basic ${token}`
      );
      httpOptions.headers = httpOptions.headers.set(
        "Body",
        `"username" : ${username}, "password" : ${password}`
      );

      const user = await this.http.post<User>(`${this.authUrl}/login`, {}, httpOptions).toPromise();

      console.log(user);

      this.isLoggedIn = true;
      this.user = user;
      return Promise.resolve(this.user);
    } catch (e) {
      console.log(e);
      return Promise.reject();
    }
  }

  logout() {
    httpOptions.headers = httpOptions.headers.set("Authorization", ``);
    this.isLoggedIn = false;
    this.user = null;
  }

  loggedInUser() : User{
    return this.user;
  }
}
