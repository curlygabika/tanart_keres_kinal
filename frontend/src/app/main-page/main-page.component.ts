import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  private name = 'Erik';

  constructor() { }

  ngOnInit() {
  }

  changeName() {
    this.name = 'Megvaltoz!';
  }

}
