import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {QuizQuestionsComponent} from "./home/quiz-questions/quiz-questions.component";
import {LoseWeightComponent} from "./home/lose-weight/lose-weight.component";
import {PersonalQuestionsComponent} from "./home/personal-questions/personal-questions.component";
import {LoginComponent} from "./login/login.component";
import {EmailComponent} from "./home/email/email.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full',
  },
  //TODO Not sure if next two paths should be here!
  {
    path: 'quiz/questions',
    component: QuizQuestionsComponent
  },
  {
    path: 'quiz/lose-weight',
    component: LoseWeightComponent
  },
  {
    path: 'quiz/personal-questions',
    component: PersonalQuestionsComponent
  },
  {
    path: 'quiz/email',
    component: EmailComponent
  },
  {
    path: 'account/create',
    component: LoginComponent
  },
  {
    path: '**',
    component: HomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
