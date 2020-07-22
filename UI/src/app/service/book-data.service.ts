import { Injectable } from '@angular/core';
import { API_URL } from '../app.constants';
import { HttpClient } from '@angular/common/http';
import { Book } from '../list-book/list-book.component';

@Injectable({
  providedIn: 'root'
})
export class BookDataService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllBooks(libraryId) {
    return this.http.get<Book[]>(`${API_URL}/libraries/${libraryId}/books`);
    //console.log("Execute Hello World Bean Service")
  }

  deleteBook(libraryId,id){
    return this.http.delete(`${API_URL}/libraries/${libraryId}/books/${id}`);
  }

  retrieveBook(libraryId,id){
    return this.http.get<Book>(`${API_URL}/libraries/${libraryId}/books/${id}`);
  }

  updateBook(libraryId,id, book){
    return this.http.put(
          `${API_URL}/libraries/${libraryId}/books/${id}`
                , book);
  }

  createBook(libraryId,book){
    return this.http.post(
              `${API_URL}/libraries/${libraryId}/books`
                , book);
  }

}
