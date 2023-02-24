import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-family-package-questions',
  templateUrl: './family-package-questions.component.html',
  styleUrls: ['./family-package-questions.component.scss']
})
export class FamilyPackageQuestionsComponent implements OnInit {
  public isSelectionDisabled: boolean = false;

  constructor(private readonly router: Router) {
  }

  public ngOnInit(): void {
  }

  public onValueChange() {
    this.isSelectionDisabled = true;
  }

  onButtonChange() {
  }
}
