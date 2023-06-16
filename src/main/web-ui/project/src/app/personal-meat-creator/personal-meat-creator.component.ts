import {Component, OnInit, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {MatTable} from "@angular/material/table";
import {ArrowDivDirective} from './arrow-div.directive';
import {KeyBoardService} from './keyboard.service';
import {FoodNutrientsService} from "../service/food-nutrients.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-personal-meat-creator',
  templateUrl: './personal-meat-creator.component.html',
  styleUrls: ['./personal-meat-creator.component.scss']
})
export class PersonalMeatCreatorComponent implements OnInit {
  @ViewChild(MatTable, {static: true})
  table: MatTable<any> //used when add a row, see comment in function add()

  @ViewChildren(ArrowDivDirective)
  inputs: QueryList<ArrowDivDirective>

  displayedColumns: string[] = ['Food Name', 'Calories', 'Protein', 'Calcium', 'Iron', 'VitaminA', 'Thiamine', 'Riboflavin', 'Niacin', 'Ascorbic Acid', 'delete'];

  foodFormArray = new FormArray([
      new FormGroup(
        {
          foodName: new FormControl("Pizza"),
          calories: new FormControl(0),
          protein: new FormControl(0),
          calcium: new FormControl(0),
          iron: new FormControl(0),
          vitaminA: new FormControl(0),
          thiamine: new FormControl(0),
          riboflavin: new FormControl(0),
          niacin: new FormControl(0),
          ascorbicAcid: new FormControl(0)
        })
    ]
  )

  public constructor(private router: Router,
                     private readonly keyboardService: KeyBoardService,
                     private readonly foodNutrientsService: FoodNutrientsService) {
  }

  public ngOnInit(): void {
    this.keyboardService.keyBoard.subscribe(res => {
      this.move(res)
    })
  }

  public delete(index: number): void {
    this.foodFormArray.removeAt(index);
    this.table.renderRows()
  }

  public add(): void {
    this.foodFormArray.push(new FormGroup({
      foodName: new FormControl(""),
      calories: new FormControl(0),
      protein: new FormControl(0),
      calcium: new FormControl(0),
      iron: new FormControl(0),
      vitaminA: new FormControl(0),
      thiamine: new FormControl(0),
      riboflavin: new FormControl(0),
      niacin: new FormControl(0),
      ascorbicAcid: new FormControl(0)
    }))
    this.table.renderRows()
  }

  public move(object: any): void {
    const inputToArray = this.inputs.toArray()
    const rows = this.foodFormArray.length
    const cols = this.displayedColumns.length
    let index = inputToArray.findIndex(x => x.element === object.element)
    switch (object.action) {
      case "UP":
        index--;
        break;
      case "DOWN":
        index++;
        break;
      case "LEFT":
        if (index - rows >= 0)
          index -= rows;
        else {
          let rowActual = index % rows;
          if (rowActual > 0)
            index = (rowActual - 1) + (cols - 1) * rows;
        }
        break;
      case "RIGTH":
        console.log(index + rows, inputToArray.length)
        if (index + rows < inputToArray.length)
          index += rows;
        else {
          let rowActual = index % rows;
          if (rowActual < rows - 1)
            index = (rowActual + 1);

        }
        break;
    }
    if (index >= 0 && index < this.inputs.length) {
      inputToArray[index].element.nativeElement.focus();
    }
  }

  public onSaveButtonClick(): void {
    const buildFoodNutrients = this.buildFoodNutrients();
    console.log(buildFoodNutrients)
    this.foodNutrientsService.saveFoodNutrients(buildFoodNutrients).subscribe(() => {
      alert('Vous avez réussi à sauvegarder vos aliments !')
      this.router.navigate(['/quiz/questions'])
    });
  }

  private buildFoodNutrients(): any {
    return this.foodFormArray.controls.map((formGroup) => {
      return {
        foodName: formGroup.controls['foodName'].value,
        calories: formGroup.controls['calories'].value,
        protein: formGroup.controls['protein'].value,
        calcium: formGroup.controls['calcium'].value,
        iron: formGroup.controls['iron'].value,
        vitaminA: formGroup.controls['vitaminA'].value,
        thiamine: formGroup.controls['thiamine'].value,
        riboflavin: formGroup.controls['riboflavin'].value,
        niacin: formGroup.controls['niacin'].value,
        ascorbicAcid: formGroup.controls['ascorbicAcid'].value,
        owner: localStorage.getItem('username')
      };
    });
  }
}
