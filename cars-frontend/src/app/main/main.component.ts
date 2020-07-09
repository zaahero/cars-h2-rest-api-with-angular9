import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  @Input() model: string;
  @Input() make: string;
  @Input() colour: string;
  @Input() builtdate: string;
  @Input() doors: string;
  @Input() enginesize: string;


  @Input() makePop: string;
  @Input() modelPop: string;
  @Input() colourPop: string;
  @Input() builtdatePop: string;
  @Input() doorsPop: string;
  @Input() enginesizePop: string;
  @Input() contentPop: string;

  currentCarId: number;
  carList: any;

  constructor(public http: HttpClient) {
  }

  ngOnInit() {
    this.getAllCars();
  }

  createCar() {
     console.log(this.model , this.make , this.colour , this.builtdate , this.doors, this.enginesize);
    let request = this.createCarObject();
    return this.http.post('http://localhost:8080/api/v1/cars', request)
      .subscribe(response => {
        this.clearCreatCarFields();
        console.log(response);
        this.getAllCars();
      });
  }

  getAllCars() {
    this.http.get('http://localhost:8080/api/v1/cars')
      .subscribe(response => {
        console.log(response);
        this.carList = response;
      })
  }

  deleteCar(id: any) {
    this.http.delete('http://localhost:8080/api/v1/cars/' + id)
      .subscribe(response => {
        this.getAllCars();
      });
  }

  editCar(id: number) {
    this.currentCarId = id;
    console.log(id)
    this.makePop = this.carList[id].make;
    this.modelPop = this.carList[id].model;
    this.colourPop = this.carList[id].colour;
    this.builtdatePop = this.carList[id].builtdate;
    this.doorsPop = this.carList[id].doors;
    this.enginesizePop = this.carList[id].enginesize;
    // this.contentPop = this.car[id];

  }

  saveCar() {
    this.carList[this.currentCarId].make = this.makePop;
    this.carList[this.currentCarId].model = this.modelPop;
    this.carList[this.currentCarId].colour = this.colourPop;
    this.carList[this.currentCarId].builtdate = this.builtdatePop;
    this.carList[this.currentCarId].doors = this.doorsPop;
    this.carList[this.currentCarId].enginesize = this.enginesizePop;
    this.http.put('http://localhost:8080/api/v1/cars', this.carList[this.currentCarId])
      .subscribe(response => {
        this.getAllCars();
      });
  }

  createCarObject() {
    const request = {
      make: this.make,
      model: this.model,
      colour: this.colour,
      // price: 30,
      builtdate: 30,
      doors: 30,
      enginesize: 30
    }

    return request;
  }

  clearCreatCarFields() {
    this.make = undefined;
    this.model = undefined;
    this.colour = undefined;
    this.builtdate = undefined;
    this.doors = undefined;
    this.enginesize = undefined;
  }

}
