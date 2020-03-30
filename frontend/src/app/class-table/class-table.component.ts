import { Component, OnInit } from '@angular/core';
import { ClassService } from '../class.service';
import { Task } from '../task';

@Component({
  selector: 'app-class-table',
  templateUrl: './class-table.component.html',
  styleUrls: ['./class-table.component.css']
})
export class ClassTableComponent implements OnInit {

  public tasks: Task[];
  public columns: string[];

  constructor(private classService: ClassService) { }

  async ngOnInit() {
    this.tasks = await this.classService.getTasks();
    this.columns = this.classService.getColumns();
  }

}
