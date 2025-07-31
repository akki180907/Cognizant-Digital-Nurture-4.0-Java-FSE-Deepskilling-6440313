import { render, screen } from '@testing-library/react';
import App from './App';

test('renders GitHub heading for techiesyed', () => {
  render(<App />);
  const heading = screen.getByText(/GitHub Repositories for techiesyed/i);
  expect(heading).toBeInTheDocument();
});
