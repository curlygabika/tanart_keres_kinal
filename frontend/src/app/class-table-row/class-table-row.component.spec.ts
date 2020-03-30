import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassTableRowComponent } from './class-table-row.component';

describe('ClassTableRowComponent', () => {
  let component: ClassTableRowComponent;
  let fixture: ComponentFixture<ClassTableRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassTableRowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassTableRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
