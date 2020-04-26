import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { ClassService } from '../class.service';
import { Task } from '../task';

@Component({
  selector: 'app-my-classes',
  templateUrl: './my-classes.component.html',
  styleUrls: ['./my-classes.component.css']
})
export class MyClassesComponent implements OnInit {

  public filteredTasks: Task[];
  public cloumns: string[];
  public something: string[];

  constructor(
    private classService: ClassService,
    private authService: AuthService
  ) { }

  async ngOnInit() {
    this.filteredTasks = [];
    const tasks = await this.classService.getTasks();
    this.cloumns = this.classService.getColumns();

    for (let task of tasks) {
      if (task.createdBy.fullName == this.authService.user.fullName) {
        this.filteredTasks.push(task);
      }
    }
  }

  onDeleteTask(task: Task) {
    this.classService.deleteTask(task);
  }

}
