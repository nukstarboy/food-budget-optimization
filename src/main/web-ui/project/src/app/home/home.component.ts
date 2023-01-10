import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PlanService} from "../service/plan.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(private readonly router: Router,
              private readonly planService: PlanService) { }

  ngOnInit(): void {
    this.planService.getString()
      .subscribe((text) => console.log(text))
  }

  onButtonClick() {
    this.router.navigate(['/quiz/questions']);
  }

}
