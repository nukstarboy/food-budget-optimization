import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatMenuModule} from "@angular/material/menu";
import {HomeComponent} from "./home/home.component";
import {QuizQuestionsComponent} from "./home/quiz-questions/quiz-questions.component";
import {LoseWeightComponent} from "./home/lose-weight/lose-weight.component";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    QuizQuestionsComponent,
    LoseWeightComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatSlideToggleModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
