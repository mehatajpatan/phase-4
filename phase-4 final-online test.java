Online Test Application Code :-

Quiz.html –
<div id="quiz" class="container mt-5">
  <h2 class="text-center fw-bold text-uppercase">{{ quiz.name }}</h2>
  <hr />

  <div *ngIf="mode == 'quiz' && quiz">
    <div *ngFor="let question of filteredQuestions">
      <div class="mb-4">
        <span class="badge bg-info">
          Question {{ pager.index + 1 }} of {{ pager.count }}.
        </span>

        <span *ngIf="config.duration" class="badge bg-info float-end">
          Time: {{ ellapsedTime }} / {{ duration }}
        </span>
      </div>

      <h3 class="fw-normal mb-4">
        {{ pager.index + 1 }}.
        <span [innerHTML]="question.name"></span>
      </h3>
      <div class="row text-left options">
        <div class="col-6" *ngFor="let option of question.options">
          <div class="option">
            <label class="fw-normal" [attr.for]="option.id">
              <input
                id="{{ option.id }}"
                type="checkbox"
                [(ngModel)]="option.selected"
                (change)="onSelect(question, option)"
              />
              {{ option.name }}
            </label>
          </div>
        </div>
      </div>
    </div>
    <hr />
    <br />
    <div class="text-center">
      <button
        class="btn btn-outline-primary mx-2"
        *ngIf="config.allowBack"
        (click)="goTo(0)"
      >
        First
      </button>
      <button
        class="btn btn-outline-primary mx-2"
        *ngIf="config.allowBack"
        (click)="goTo(pager.index - 1)"
      >
        Prev
      </button>
      <button
        class="btn btn-outline-primary mx-2"
        (click)="goTo(pager.index + 1)"
      >
        Next
      </button>
      <button
        class="btn btn-outline-primary mx-2"
        *ngIf="config.allowBack"
        (click)="goTo(pager.count - 1)"
      >
        Last
      </button>
    </div>
    <br />
  </div>

  <div class="row text-center" *ngIf="mode == 'review'">
    <div
      class="col-4 cursor-pointer"
      *ngFor="let question of quiz.questions; let index = index"
    >
      <div
        (click)="goTo(index)"
        class="p-3 mb-2 {{
          isAnswered(question) == 'Answered' ? 'bg-info' : 'bg-warning'
        }}"
      >
        {{ index + 1 }}. {{ isAnswered(question) }}
      </div>
    </div>
  </div>
  <div class="result" *ngIf="mode == 'result'">
    <h2>
      Quiz Result:
      <span class="badge bg-success"
        >Your Score is: {{ quizScore }} Out of {{ quizTotalScore }}</span
      >
    </h2>
    <div *ngFor="let question of quiz.questions; let index = index">
      <div class="result-question">
        <h5>{{ index + 1 }}. {{ question.name }}</h5>
        <div class="row">
          <div class="col-6" *ngFor="let Option of question.options">
            <input
              id="{{ Option.id }}"
              type="checkbox"
              disabled="disabled"
              [(ngModel)]="Option.selected"
            />
            {{ Option.name }}
          </div>
        </div>
        <div
          class="p-1 m-2 alert {{
            isCorrect(question) == 'correct' ? 'alert-success' : 'alert-danger'
          }}"
        >
          Your answer is {{ isCorrect(question) }}.
        </div>
      </div>
    </div>
    <h4 class="alert alert-info text-center">You may close this window now.</h4>
  </div>
  <hr />
  <div *ngIf="mode != 'result'">
    <button class="btn btn-warning mx-2" (click)="mode = 'quiz'">Quiz</button>
    <button class="btn btn-info mx-2" (click)="mode = 'review'">Review</button>
    <button class="btn btn-primary mx-2" (click)="onSubmit()">
      Submit Quiz
    </button>
  </div>
</div>


Quiz-List.html –
<div class="container mt-5">
  <div class="row">
    <div *ngFor="let tempQuiz of quizes" class="col-md-4">
      <div class="card">
        <img
          src="{{ tempQuiz.imageUrl }}"
          class="card-img-top"
          alt="Quiz Image"
        />
        <div class="card-body text-center">
          <h5 class="card-title">
            {{ tempQuiz.name }}
          </h5>
          <hr />
          <p class="card-text">
            {{ tempQuiz.description }}
          </p>
          <a
            routerLink="/quiz/{{ tempQuiz.name }}"
            class="btn btn-outline-primary"
            >Give {{ tempQuiz.name }} Quiz</a
          >
        </div>
      </div>
    </div>
  </div>
</div>


App-Component.html –
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand ml-2 fw-bolder" routerLink="/quiz">Online Quiz</a>
  <a class="navbar-brand ml-2 fw-bolder" routerLink="/login">Login</a>
  <app-login></app-login>
  <button
    class="navbar-toggler"
    type="button"
    data-toggle="collapse"
    data-target="#navbarNav"
    aria-controls="navbarNav"
    aria-expanded="false"
    aria-label="Toggle navigation"
  >
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav ms-auto">
      <li class="nav-item">
        <a
          class="nav-link btn-outline-primary btn-sm text-light fw-bolder"
          routerLink="/quiz"
          >Home</a
        >
      </li>
    </ul>
  </div>
</nav>

<div class="container">
  <router-outlet></router-outlet>
</div>

<footer class="bg-dark text-center text-lg-start mt-5">
  <!-- Copyright -->
  <div class="text-center p-3 text-light fw-bold">
    © 2022 Copyright:
    <a class="text-light fw-bold" routerLink="/quiz">Online Quiz Application</a>
  </div>
  <!-- Copyright -->
</footer>


Index.html –
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>OnlineTestApplication</title>
  <base href="/">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" type="image/x-icon" href="favicon.ico">
</head>
<body>
  <app-root></app-root>
</body>
</html>


