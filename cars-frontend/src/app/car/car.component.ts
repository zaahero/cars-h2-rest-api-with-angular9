
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {

  @Input() car: any;
  @Input() index: number;
  @Output() carDeleted = new EventEmitter<number>();
  @Output() carEdited = new EventEmitter<any>();
  isClicked = false;

  constructor(public http: HttpClient) { }

  ngOnInit() {

  }

  toggleCar() {
    this.isClicked = !this.isClicked;
  }

  handleEdit() {
    this.carEdited.emit(this.index);
  }

  handelDelete() {
    this.carDeleted.emit(this.car.id);
  }

}
