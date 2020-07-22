import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from 'node_modules/@angular/router';
import { BookDataService } from '../service/book-data.service';
import { Book } from '../list-book/list-book.component';
import { LibraryDataService } from '../service/library-data.service';
import { Library } from '../list-library/list-library.component';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  id:number
  book: Book
  libraryId: number;
  library: Library;

  constructor(
    private bookService: BookDataService,
    private libraryDataService: LibraryDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    
    this.id = this.route.snapshot.params['id'];
    this.libraryId = this.route.snapshot.params['libraryId'];
    
    this.book = new Book(this.id,'','','','',new Date());
    this.library = new Library(this.libraryId,'','');
    this.libraryDataService.retrieveLibrary(this.libraryId).subscribe(
      response => {
        this.library = response;
      }
    )
    if(this.id!=-1) {
      this.bookService.retrieveBook(this.libraryId, this.id)
          .subscribe (
            data => this.book = data
          )
    }
  }

  saveBook() {
    if(this.id == -1) { //=== ==
      this.bookService.createBook(this.libraryId,this.book)
          .subscribe (
            data => {
              console.log(data)
              this.router.navigate(['library', this.libraryId])
            }
          )
    } else {
      this.bookService.updateBook(this.libraryId,this.id, this.book)
          .subscribe (
            data => {
              console.log(data)
              this.router.navigate(['library', this.libraryId])
            }
          )
    }
  }

}
