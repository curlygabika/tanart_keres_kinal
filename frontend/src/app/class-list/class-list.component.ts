import { Component, OnInit } from '@angular/core';
import { ClassService } from '../class.service';
import { Task } from '../task';


@Component({
  selector: 'app-class-list',
  templateUrl: './class-list.component.html',
  styleUrls: ['./class-list.component.css']
})
export class ClassListComponent implements OnInit {

  tasks: Task[];
  selectedTask: Task;
  tableColumns: string[];

  constructor(private classService: ClassService) { }

  async ngOnInit() {
      this.tasks = await this.classService.getTasks();
      this.tableColumns = this.classService.getColumns();
      this.selectedTask = this.tasks[0];
      console.log(this.tasks);
  }

  onSelectTask(task: Task): void {
    this.selectedTask = task;
    console.log(this.selectedTask);
  }




}
