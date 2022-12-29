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

  onButtonClick() {
    this.router.navigate(['/quiz/lose-weight']);
  }

}
