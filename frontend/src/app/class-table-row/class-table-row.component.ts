import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-class-table-row',
  templateUrl: './class-table-row.component.html',
  styleUrls: ['./class-table-row.component.css']
})
export class ClassTableRowComponent implements OnInit {

  @Input() task: any;
  @Input() columns: string[];

  constructor() { }

  ngOnInit() {
  }

}
