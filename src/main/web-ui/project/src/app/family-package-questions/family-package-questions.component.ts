import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-family-package-questions',
  templateUrl: './family-package-questions.component.html',
  styleUrls: ['./family-package-questions.component.scss']
})
export class FamilyPackageQuestionsComponent implements OnInit {
  private selectedMembers: number;

  constructor(private readonly router: Router) {
  }

  public ngOnInit(): void {
  }

  public onValueChange(selectedMembers: number) {
    this.selectedMembers = selectedMembers;
  }

  public onDoneClick(): void {
    localStorage.setItem('selectedMembers', String(this.selectedMembers))
    this.router.navigate(['/quiz/family-questions']);
  }
}
