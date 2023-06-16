import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FoodNutrientsService} from "../service/food-nutrients.service";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-family-package-questions',
  templateUrl: './family-package-questions.component.html',
  styleUrls: ['./family-package-questions.component.scss']
})
export class FamilyPackageQuestionsComponent implements OnInit {
  public doesUserHavePersonalFood: boolean = false;
  public formGroup = new FormGroup({
    isToggleTriggered: new FormControl(false)
  })
  public isButtonDisable: boolean = true;

  private selectedMembers: number;

  public constructor(private readonly router: Router,
                     private readonly foodNutrientsService: FoodNutrientsService) {
  }

  public ngOnInit(): void {
    this.foodNutrientsService.existByOwner(localStorage.getItem('username')).subscribe((exists: boolean) => this.doesUserHavePersonalFood = exists)
  }

  public onValueChange(selectedMembers: number) {
    this.selectedMembers = selectedMembers;
    this.isButtonDisable = false;
  }

  public onDoneClick(): void {
    localStorage.setItem('selectedMembers', String(this.selectedMembers))
    localStorage.setItem('isToggleTriggered', String(this.formGroup.controls['isToggleTriggered'].value))
    this.router.navigate(['/quiz/family-questions']);
  }
}
