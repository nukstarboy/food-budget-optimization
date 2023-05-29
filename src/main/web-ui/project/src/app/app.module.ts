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
import {QuizQuestionsComponent} from "./quiz-questions/quiz-questions.component";
import {MatStepperModule} from "@angular/material/stepper";
import {MatFormFieldModule} from "@angular/material/form-field";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {PersonalQuestionsComponent} from './personal-questions/personal-questions.component';
import {LoginComponent} from './login/login.component';
import {EmailComponent} from './email/email.component';
import {HttpClientModule} from "@angular/common/http";
import {PlanService} from "./service/plan.service";
import {PersonalQuestionsService} from "./service/personal-questions.service";
import {SignupComponent} from './signup/signup.component';
import {ProfileComponent} from './profile/profile.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";
import {FoodPriceComponent} from './profile/food-price/food-price.component';
import {NutrientsComponent} from './profile/nutrients/nutrients.component';
import {SolveTimeComponent} from './profile/solve-time/solve-time.component';
import {MatTabsModule} from "@angular/material/tabs";
import {HomePageService} from "./service/home-page.service";
import {JwtHelperService} from "@auth0/angular-jwt";
import {HelpStaticComponent} from './help-static/help-static.component';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {PaymentMethodComponent} from './payment-method/payment-method.component';
import {PaymentTaxComponent} from './payment-method/payment-tax/payment-tax.component';
import {MatRadioModule} from "@angular/material/radio";
import {BankDetailsComponent} from './payment-method/payment-tax/bank-details/bank-details.component';
import {FamilyPackageQuestionsComponent} from './family-package-questions/family-package-questions.component';
import {FamilyQuestionsComponent} from './family-package-questions/family-questions/family-questions.component';
import {PersonalFoodComponent} from './profile/personal-food/personal-food.component';
import {FamilyFoodComponent} from './profile/family-food/family-food.component';
import {FamilyFoodPriceComponent} from "./profile/family-food-price/family-food-price.component";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    QuizQuestionsComponent,
    PersonalQuestionsComponent,
    LoginComponent,
    EmailComponent,
    SignupComponent,
    ProfileComponent,
    FoodPriceComponent,
    NutrientsComponent,
    SolveTimeComponent,
    HelpStaticComponent,
    PaymentMethodComponent,
    PaymentTaxComponent,
    BankDetailsComponent,
    FamilyPackageQuestionsComponent,
    FamilyQuestionsComponent,
    PersonalFoodComponent,
    FamilyFoodComponent,
    FamilyFoodPriceComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    MatSlideToggleModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatMenuModule,
    MatStepperModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    MatPaginatorModule,
    MatTableModule,
    MatTabsModule,
    MatCheckboxModule,
    MatRadioModule
  ],
  providers: [PlanService, PersonalQuestionsService, HomePageService, JwtHelperService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
