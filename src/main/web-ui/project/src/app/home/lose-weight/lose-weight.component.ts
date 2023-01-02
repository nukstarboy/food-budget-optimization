import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-lose-weight',
  templateUrl: './lose-weight.component.html',
  styleUrls: ['./lose-weight.component.scss']
})
export class LoseWeightComponent {
  public constructor(private readonly router: Router) {}

  public onYesButtonClick(): void {
    this.router.navigate(['/quiz/personal-questions']);
    console.log('Yes, should be save!')
  }

  public onNoButtonClick(): void {
    this.router.navigate(['/quiz/personal-questions']);
    console.log('No, should be save!')
  }
}
