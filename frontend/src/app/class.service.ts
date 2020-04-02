import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Task } from './task';


const httpOptions = {
  headers: new HttpHeaders({ 
    'Content-Type': 'application/json',
    'Authorization': 'Basic YWRtaW46cGFzc3dvcmQ=', // admin/password
  })
};

interface FeathersResponse<T> {
  total: number,
  limit: number,
  skip: number,
  data: T[]
};

@Injectable({
  providedIn: 'root'
})
export class ClassService {

  private classesUrl = 'http://localhost:8080/task';
  
  constructor(private http: HttpClient) { }
  
  deleteTask(task: Task) {
    const ret = this.http.delete(`${this.classesUrl}/${task.id}`, httpOptions).toPromise();
    //console.log(ret);
  }

  getTasks(): Promise<Task[]> {

    const tasks = this.http.get<Task[]>(`${this.classesUrl}`, httpOptions).toPromise();
    //console.log(tasks);
    return tasks; 
  }

  getColumns(): string[] {
    return ["Azonosító", "Cím", "Helyszín", "Ár"];
  }

  addNewClass(title, place, price, description, subjectType) {
    const id = Math.floor(Math.random() * 10000) + 1;

    const newTask = this.http.put(this.classesUrl, {id ,title, place, price, description, subjectType}, httpOptions).toPromise();
    console.log(newTask);
  }

}
