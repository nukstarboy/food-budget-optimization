import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {QuizQuestionsComponent} from "./home/quiz-questions/quiz-questions.component";
import {PersonalQuestionsComponent} from "./home/personal-questions/personal-questions.component";
import {LoginComponent} from "./login/login.component";
import {EmailComponent} from "./home/email/email.component";
import {SignupComponent} from "./home/signup/signup.component";

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
    path: 'quiz/personal-questions',
    component: PersonalQuestionsComponent
  },
  {
    path: 'quiz/email',
    component: EmailComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: SignupComponent
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
