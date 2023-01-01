import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-lose-weight',
  templateUrl: './lose-weight.component.html',
  styleUrls: ['./lose-weight.component.scss']
})
export class LoseWeightComponent {
  constructor(private readonly router: Router) { }

  onYesButtonClick() {
    this.router.navigate(['/quiz/self-questions']);
    console.log('Yes, should be save!')
  }

  onNoButtonClick() {
    this.router.navigate(['/quiz/self-questions']);
    console.log('No, should be save!')
  }
}
