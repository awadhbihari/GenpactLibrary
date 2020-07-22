import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LibraryDataService } from '../service/library-data.service';
import "@angular/compiler"

export class Library {
  constructor(
    public id: number,
    public libraryName: string,
    public description: string
  ){

  }
}
@Component({
  selector: 'app-list-library',
  templateUrl: './list-library.component.html',
  styleUrls: ['./list-library.component.css']
})
export class ListLibraryComponent implements OnInit {

  libraries: Library[]

  message: string

  constructor(    private libraryService:LibraryDataService,
    private router : Router
  ) { }

  ngOnInit() {
    this.refreshLibraries();
  }

  refreshLibraries(){
    this.libraryService.retrieveAllLibraries().subscribe(
      response => {
        console.log(response);
        this.libraries = response;
      }
    )
  }
}
