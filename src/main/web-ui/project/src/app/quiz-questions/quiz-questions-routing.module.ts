import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {QuizQuestionsComponent} from "./quiz-questions.component";

const route: Routes = [
  {
    path: '',
    component: QuizQuestionsComponent,
    children: [
      {
        path: 'lose-weight',
        loadChildren: () => import('./lose-weight/lose-weight.module').then(m => m.LoseWeightModule)
      }
    ]
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(route)
  ]
})
export class QuizQuestionsRoutingModule { }
