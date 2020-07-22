import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BookDataService } from '../service/book-data.service';
import { LibraryDataService } from '../service/library-data.service';
import { Library } from '../list-library/list-library.component';


export class Book {
  constructor(
    public id: number,
    public bookName: string,
    public description: string,
    public author: string,
    public library: string,
    public publishDate: Date
  ){

  }
}

@Component({
  selector: 'app-list-book',
  templateUrl: './list-book.component.html',
  styleUrls: ['./list-book.component.css']
})
export class ListBookComponent implements OnInit {

  books: Book[]

  message: string
  library:Library
  libraryId: number;

  constructor(
    private route: ActivatedRoute,
    private router : Router,
    private bookService:BookDataService,
    private libraryDataService : LibraryDataService
  ) { }

  ngOnInit() {
    this.libraryId = this.route.snapshot.params['libraryId'];
    this.library = new Library(this.libraryId,'','');
    this.refreshBooks();
    this.libraryDataService.retrieveLibrary(this.libraryId).subscribe(
      response => {
        this.library = response;
        this.message = 'All books of '+ this.library.libraryName +' libraray.';
      }
    )
  }

  refreshBooks(){
    //this.books = [new Book(1,'Kel','mail','sale','pub',new Date()), new Book(2,'jai','ads','pa','pub',new Date())]
    this.bookService.retrieveAllBooks(this.libraryId).subscribe(
      response => {
        console.log(response);
        this.books = response;
      }
    )
  }

  deleteBook(id) {
    console.log(`delete book ${id}` )
    this.bookService.deleteBook(this.libraryId,id).subscribe (
      response => {
        console.log(response);
        this.message = `Delete of Book ${id} Successful!`;
        this.refreshBooks();
      }
    )
  }

  updateBook(id) {
    console.log(`update ${id}`)
    this.router.navigate(['library',this.libraryId,'book',id])
  }

  addBook() {
    this.router.navigate(['library',this.libraryId,'book',-1])
  }
}
