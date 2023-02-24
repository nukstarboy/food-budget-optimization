import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {QuizQuestionsComponent} from "./quiz-questions/quiz-questions.component";
import {PersonalQuestionsComponent} from "./personal-questions/personal-questions.component";
import {LoginComponent} from "./login/login.component";
import {EmailComponent} from "./email/email.component";
import {SignupComponent} from "./signup/signup.component";
import {ProfileComponent} from "./profile/profile.component";
import {HelpStaticComponent} from "./help-static/help-static.component";
import {PaymentTaxComponent} from "./payment-method/payment-tax/payment-tax.component";
import {FamilyPackageQuestionsComponent} from "./family-package-questions/family-package-questions.component";
import {FamilyQuestionsComponent} from "./family-package-questions/family-questions/family-questions.component";

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
    path: 'payment',
    component: PaymentTaxComponent
  },
  {
    path: 'quiz/family-package',
    component: FamilyPackageQuestionsComponent
  },
  {
    path: 'quiz/family-questions',
    component: FamilyQuestionsComponent
  },
  {
    path: 'help',
    component: HelpStaticComponent
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'profile',
    component: ProfileComponent
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
