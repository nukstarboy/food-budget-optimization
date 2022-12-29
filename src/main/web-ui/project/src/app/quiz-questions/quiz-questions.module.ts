import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {QuizQuestionsComponent} from "./quiz-questions.component";
import {QuizQuestionsRoutingModule} from "./quiz-questions-routing.module";
import {MatButtonModule} from "@angular/material/button";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [QuizQuestionsComponent],
  imports: [
    CommonModule,
    QuizQuestionsRoutingModule,
    MatButtonModule,
    RouterModule
  ]
})
export class QuizQuestionsModule { }
