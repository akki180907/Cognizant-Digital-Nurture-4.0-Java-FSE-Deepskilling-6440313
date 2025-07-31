import React from 'react';
import { render, screen } from '@testing-library/react';
import CohortDetails from './CohortDetails';
import { CohortsData } from './Cohort';

describe("CohortDetails Component", () => {

  test("renders the component without crashing", () => {
    render(<CohortDetails cohort={CohortsData[0]} />);
    expect(screen.getByRole("heading", { level: 3 })).toBeInTheDocument();
  });

  test("displays cohort code and technology in h3", () => {
    const cohort = CohortsData[1];
    render(<CohortDetails cohort={cohort} />);
    const heading = screen.getByRole("heading", { level: 3 });
    expect(heading).toHaveTextContent(`${cohort.cohortCode} -`);
    expect(heading).toHaveTextContent(cohort.technology);
  });

  test("shows all cohort details correctly", () => {
    const cohort = CohortsData[2];
    render(<CohortDetails cohort={cohort} />);
    expect(screen.getByText("Started On")).toBeInTheDocument();
    expect(screen.getByText(cohort.startDate)).toBeInTheDocument();
    expect(screen.getByText("Current Status")).toBeInTheDocument();
    expect(screen.getByText(cohort.currentStatus)).toBeInTheDocument();
    expect(screen.getByText("Coach")).toBeInTheDocument();
    expect(screen.getByText(cohort.coachName)).toBeInTheDocument();
    expect(screen.getByText("Trainer")).toBeInTheDocument();
    expect(screen.getByText(cohort.trainerName)).toBeInTheDocument();
  });

  test("renders with a different cohort and verifies structure", () => {
    const cohort = CohortsData[3];
    render(<CohortDetails cohort={cohort} />);
    expect(screen.getByRole("heading", { level: 3 })).toHaveTextContent(`${cohort.cohortCode} -${cohort.technology}`);
    expect(screen.getAllByRole("definition")).toHaveLength(4);
  });

});
