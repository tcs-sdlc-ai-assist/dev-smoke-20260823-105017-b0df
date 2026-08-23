import {describe, expect, it, vi} from 'vitest';
import {fireEvent, render, screen, waitFor} from '@testing-library/react';

const mocks = vi.hoisted(() => ({post: vi.fn(() => Promise.resolve({data: {}}))}));
vi.mock('../../api/client', () => ({api: {get: vi.fn(() => Promise.resolve({data: [{id: 'card-1', name: 'Visa', last4: '4821', status: 'ACTIVE'}]})), post: mocks.post}}));
import CardsPage from './CardsPage';

describe('CardsPage', () => {
  it('sends a block request for an active card', async () => {
    render(<CardsPage />);
    fireEvent.click(await screen.findByRole('button', {name: /block card/i}));
    await waitFor(() => expect(mocks.post).toHaveBeenCalledWith('/cards/card-1/block'));
  });
});
