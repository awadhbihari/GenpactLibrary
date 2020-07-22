import { Injectable } from '@angular/core';
import { API_URL } from '../app.constants';
import { HttpClient } from '@angular/common/http';
import { Library } from '../list-library/list-library.component';

@Injectable({
  providedIn: 'root'
})
export class LibraryDataService {

  constructor(
    private http:HttpClient
  ) { }

  retrieveAllLibraries(){
    return this.http.get<Library[]>(`${API_URL}/libraries`);
  }

  retrieveLibrary(id){
    return this.http.get<Library>(`${API_URL}/libraries/${id}`);
  }
}
