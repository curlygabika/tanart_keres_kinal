import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { ClassService } from '../class.service';
import { Subject } from 'rxjs';
import { Difficulty } from '../difficulty';

@Component({
  selector: 'app-class-form',
  templateUrl: './class-form.component.html',
  styleUrls: ['./class-form.component.css']
})
export class ClassFormComponent implements OnInit {

  subjects = ["matek", "angol"];

  message: string;
  subject: any;
  diff: any;
  
  newClassForm = this.fb.group({
    title: ["", [Validators.required]],
    place: ["", [Validators.required]],
    description: ["", [Validators.required]],
    price: ["", [Validators.required]]
  });
  
  constructor(
    private classService: ClassService,
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder) { }

  ngOnInit() {
  }

  get title() {
    return this.newClassForm.get("title");
  }

  get place() {
    return this.newClassForm.get("place");
  }

  get price() {
    return this.newClassForm.get("price");
  }

  get description() {
    return this.newClassForm.get("description");
  }

  onChange(value) {
    console.log("asd");
    this.subject = value;
  }

  async onSubmit() {
    this.subject = new Subject;
    this.diff = new Difficulty;
    
    this.diff.id = 1;
    this.diff.level = "beginner";

    this.subject.id = 1;
    this.subject.name = "programming";
    this.subject.difficulty = this.diff;

    console.log(this.subject);
    try{
      this.message = null;
      this.classService.addNewClass(
        this.title.value,
        this.place.value,
        this.price.value,
        this.description.value,
        this.subject)
    } catch (e) {
      this.message = "Próbáld újra!";
    }
    
  }
}
