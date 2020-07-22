import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { ErrorComponent } from './error/error.component';
import { ListLibraryComponent } from './list-library/list-library.component';
import { ListBookComponent } from './list-book/list-book.component';
import { BookComponent } from './book/book.component';

const routes: Routes = [
  { path: '', component: WelcomeComponent  },
  { path: 'library', component: ListLibraryComponent },
  { path: 'library/:libraryId', component: ListBookComponent },
  { path: 'library/:libraryId/book/:id', component: BookComponent },
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
