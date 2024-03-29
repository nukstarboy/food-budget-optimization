import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public constructor(private readonly router: Router) {
  }

  ngOnInit(): void {
  }

  public onButtonClick(): void {
    this.router.navigate(['/login']);
  }

}
