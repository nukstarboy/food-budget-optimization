import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-quiz-questions',
  templateUrl: './quiz-questions.component.html',
  styleUrls: ['./quiz-questions.component.scss']
})
export class QuizQuestionsComponent implements OnInit {

  constructor(private readonly router: Router) { }

  ngOnInit(): void {
  }

  public onButtonClick(): void {
    this.router.navigate(['/quiz/personal-questions']);
  }

  public onFamilyPackageButtonClick(): void {
    this.router.navigate(['/quiz/family-package']);
  }

  public onAddFoodButtonClick(): void {
    this.router.navigate(['/quiz/add-personal-meat']);
  }
}
